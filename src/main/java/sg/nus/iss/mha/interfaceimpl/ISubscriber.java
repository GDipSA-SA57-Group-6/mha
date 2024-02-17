package sg.nus.iss.mha.interfaceimpl;

import java.util.List;

import sg.nus.iss.mha.model.GroupHub;
import sg.nus.iss.mha.model.User;

public interface ISubscriber {
    /**
     * 根据name搜索GroupHub
     * @param name
     * @return
     */
    List<GroupHub> searchByName(String name);

    /**
     * 根据点赞数返回GroupHub
     * @return
     */
    List<GroupHub> sortByPopularity();

    /**
     * 根据用户当前距离 返回GroupHub
     * @return
     */
    List<GroupHub> sortByDistance(double latitude, double longitude);

    /**
     * 用户确认加入一个事件
     * @param userId
     * @param groupId
     */
    boolean eventConfirm(Integer userId, long groupId);

    /**
     * 用户取消一个事件
     * @param userId
     * @param groupId
     * @return
     */
    boolean eventCancel(Integer userId, long groupId);

    /**
     * 用户喜欢一个事件
     * 暂时不写
     * @param userId
     * @param groupId
     */
    void ec_Like(Integer userId, long groupId);

    /**
     * 返回某个用户所参与拼团的事件
     * @param userId
     * @return
     */
    List<GroupHub> getSubscribedGroupHubs(Integer userId);

    /**
     * 返回某个拼单所参与的用户
     * @param groupId
     * @return
     */
    List<User> getSubscribedUsersByGroupId(Long groupId);
}

