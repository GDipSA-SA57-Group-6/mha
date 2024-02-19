package sg.nus.iss.mha.controller;

import sg.nus.iss.mha.model.GroupHub;
import sg.nus.iss.mha.model.User;
import sg.nus.iss.mha.repository.GroupHubRepository;
import sg.nus.iss.mha.repository.UserRepository;
import sg.nus.iss.mha.service.GroupHubPublisherService;
import sg.nus.iss.mha.service.GroupHubSubscriberService;
import sg.nus.iss.mha.service.GroupHubValidator;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/group-hub")
public class GroupHubController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupHubRepository groupHubRepository;

    @Autowired
    private GroupHubPublisherService groupHubPublisherService;

    @Autowired
    private GroupHubSubscriberService groupHubSubscriberService;

    private final Logger logger = LoggerFactory.getLogger(GroupHubController.class);
    @Autowired
    private ObjectMapper objectMapper; // Spring Boot自动配置了ObjectMapper


    // register GroupHub validator.
    @Autowired
    private GroupHubValidator groupHubValidator;
    @InitBinder
    private void initGroupHubBinder(WebDataBinder binder) {
        binder.addValidators((Validator)  groupHubValidator);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGroupHub(@RequestParam Integer userId, @Valid @RequestBody GroupHub inGroupHub, BindingResult bindingResult) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>("User id does not exist", HttpStatus.NOT_FOUND);
        }
        
        User user = optionalUser.get();
    
        // There are some errors in binding object.
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
    
        // Log the state of the user before setting it to the newGroupHub
        logger.info("Setting publishedBy user with ID: {} and Name: {}", user.getUserId(), user.getName());
    
        // When object reaches this level, part of it is already legal.
        GroupHub newGroupHub = new GroupHub();
        newGroupHub.setName(inGroupHub.getName());
        newGroupHub.setQuantity(inGroupHub.getQuantity());
        newGroupHub.setInitialQuantity(inGroupHub.getQuantity());
        newGroupHub.setLatitude(inGroupHub.getLatitude());
        newGroupHub.setLongitude(inGroupHub.getLongitude());
        newGroupHub.setEndTime(inGroupHub.getEndTime());
        newGroupHub.setPublishedBy(user);
        newGroupHub.setStartTime(LocalDate.now());
        groupHubRepository.save(newGroupHub);
    
        // Optionally, log the state of the newGroupHub after saving
        logger.info("Created GroupHub with ID: {} and publishedBy user ID: {}", newGroupHub.getId(), newGroupHub.getPublishedBy().getUserId());
        logger.info("Created newGroupHub: {}", newGroupHub.toString());
    
        return new ResponseEntity<>(newGroupHub, HttpStatus.CREATED);
    }
    

    @GetMapping("/subscribe")
    public ResponseEntity<?> eventSubscribe(@RequestParam Integer userId, @RequestParam Long groupId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<GroupHub> optionalGroupHub = groupHubRepository.findById(groupId);
            if(optionalGroupHub.isPresent()) {
                GroupHub groupHub = optionalGroupHub.get();
                if(groupHub.getPublishedBy().getUserId() == userId) {
                    return new ResponseEntity<>("You can't register your own group!", HttpStatus.EXPECTATION_FAILED);
                }
                if(groupHub.getQuantity() <= 0) {
                    return new ResponseEntity<>("There is no head count!", HttpStatus.EXPECTATION_FAILED);
                }
                if(LocalDate.now().isAfter(groupHub.getEndTime())) {
                    return new ResponseEntity<>("Exceed the deadline.", HttpStatus.EXPECTATION_FAILED);
                }

                if(!groupHub.getHasUsers().contains(user)) {
                    groupHubSubscriberService.eventConfirm(userId, groupId);
                    return new ResponseEntity<>("Success!", HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("The user is already in that group!", HttpStatus.EXPECTATION_FAILED);
                }
            }else {
                return new ResponseEntity<>("Group Hub does not exist!", HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cancel")
    public ResponseEntity<?> eventCancel(@RequestParam Integer userId, @RequestParam Long groupId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<GroupHub> optionalGroupHub = groupHubRepository.findById(groupId);
            if(optionalGroupHub.isPresent()) {
                GroupHub groupHub = optionalGroupHub.get();
                if(LocalDate.now().isAfter(groupHub.getEndTime())) {
                    return new ResponseEntity<>("Exceed the deadline.", HttpStatus.EXPECTATION_FAILED);
                }
                if(groupHub.getHasUsers().contains(user)) {
                    groupHubSubscriberService.eventCancel(userId, groupId);
                    return new ResponseEntity<>("Success!", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("You are not in that group yet.", HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<>("Group Hub does not exist!", HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllGroupHub() {
        try {
            // 使用自定义方法来获取所有GroupHub并强制加载publishedBy
            List<GroupHub> groupHubs = groupHubRepository.findAllWithPublishedBy();
            // 将对象序列化为JSON字符串
            String json = objectMapper.writeValueAsString(groupHubs);
            // 输出完整的JSON字符串到日志
            logger.info("All GroupHubs: {}", json);
            return ResponseEntity.ok(groupHubs); // 直接返回groupHubs，Jackson会处理序列化
        } catch (Exception e) {
            logger.error("Error retrieving GroupHub data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }    

    /*
    @GetMapping("/get")
    public ResponseEntity<?> getAllGroupHub() {
        try {
            List<GroupHub> groupHubs = groupHubRepository.findAll();
            // 将对象序列化为JSON字符串
            String json = objectMapper.writeValueAsString(groupHubs);
            // 输出完整的JSON字符串到日志
            logger.info("All GroupHubs: {}", json);
            return ResponseEntity.ok(groupHubs);
        } catch (Exception e) {
            logger.error("Error serializing GroupHub data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }
    */

    /**
     * 返回某用户所有拼团事件
     * @param userId
     * @return
     */
    @GetMapping("/getSubscription")
    public ResponseEntity<?> getUserSubscription(@RequestParam("userId") Integer userId) {
        // @RequestParam("userId")注解用于将请求参数userId映射到方法的参数userId上
        return new ResponseEntity<>(groupHubSubscriberService.getSubscribedGroupHubs(userId), HttpStatus.OK);
    }

    /**
     * 返回 某用户所有发布的订单
     * @param userId
     * @return
     */
    @GetMapping("/getPublished")
    public ResponseEntity<?> getUserPublishedGroupHubs(@RequestParam("userId") Integer userId) {
        return new ResponseEntity(groupHubPublisherService.getGroupHubPublishedBy(userId), HttpStatus.OK);
    }

    /**
     * 返回某个拼单所参与的用户
     * @param groupId
     * @return
     */
    @GetMapping("/getSubscribedUsers")
    public ResponseEntity<?> getSubscribedUsersByGroupId(@RequestParam("groupId") Long groupId) {
        return new ResponseEntity<>(groupHubSubscriberService.getSubscribedUsersByGroupId(groupId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchGroupHubs(@RequestParam("name") String name) {
        // 调用服务层的searchByName方法执行搜索
        List<GroupHub> searchResults = groupHubSubscriberService.searchByName(name);
        if (searchResults.isEmpty()) {
            return new ResponseEntity<>("No GroupHubs found matching the search criteria", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

}
