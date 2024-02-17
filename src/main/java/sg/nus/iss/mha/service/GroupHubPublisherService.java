package sg.nus.iss.mha.service;

import sg.nus.iss.mha.interfaceimpl.IPublisher;
import sg.nus.iss.mha.model.GroupHub;
import sg.nus.iss.mha.repository.GroupHubRepository;
import sg.nus.iss.mha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupHubPublisherService implements IPublisher {
    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupHubRepository groupHubRepository;

    /**
     * 默认验证在controller里面，到达这一层的时候已经是绝对合法的GroupHub了。
     * @param groupHub
     * @return
     */
    @Override
    public GroupHub publish(GroupHub groupHub) {
        groupHubRepository.save(groupHub);

        return groupHub;
    }

    /**
     * 暂时不写
     * @param groupHub
     * @return
     */
    @Override
    public GroupHub modify(GroupHub groupHub) {
        return null;
    }

    /**
     * 暂时不写
     * @param groupHubID
     */
    @Override
    public void deleteByGroupHubID(Long groupHubID) {

    }

    /**
     * 对 返回某用户所有发布订单 接口的实现
     * @param userId
     * @return
     */
    @Override
    public List<GroupHub> getGroupHubPublishedBy(Integer userId) {
        List<GroupHub> items = groupHubRepository.findAll();
        items = items.stream()
                .filter(item -> item.getPublishedBy().getUserId().equals(userId))
                .toList();
        return items;
    }
}
