
package sg.nus.iss.mha.interfaceimpl;

import sg.nus.iss.mha.model.GroupHub;

import java.util.List;

public interface IPublisher {
    /**
     * 发布一个活动
     * 活动内部已经对用户进行了绑定
     * 所以不需要额外的user id
     * @param groupHub
     * @return
     */
    GroupHub publish(GroupHub groupHub);

    /**
     * 暂时不编写
     * @param groupHub
     * @return
     */
    GroupHub modify(GroupHub groupHub);

    /**
     * 暂时不编写
     * @param groupHubID
     */
    void deleteByGroupHubID(Long groupHubID);

    /**
     * 返回某个用户所发布的订单
     * @param userId
     * @return
     */
    List<GroupHub> getGroupHubPublishedBy(Integer userId);
}
