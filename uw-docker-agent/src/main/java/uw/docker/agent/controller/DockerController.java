package uw.docker.agent.controller;

import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.PortBinding;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uw.docker.agent.service.DockerClientService;

/**
 * docker的控制器。
 */
@RestController
@RequestMapping("/docker")
public class DockerController {


    /**
     * 创建一个容器。
     */
    @RequestMapping(value = "/createContainer", method = { RequestMethod.POST })
    public void createContainer(){
//        ContainerConfig config = ContainerConfig.builder().

    }

    /**
     * 关闭一个容器
     * @param containerId
     * @param secondsToWaitBeforeKilling
     */
    @RequestMapping(value = "/stopContainer", method = { RequestMethod.GET })
    public void stopContainer(final String containerId, final int secondsToWaitBeforeKilling){
        try {
            DockerClientService.getDocker().stopContainer(containerId,secondsToWaitBeforeKilling);
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动一个容器。
     * @param containerId
     */
    @RequestMapping(value = "/startContainer", method = { RequestMethod.GET })
    public void startContainer(final String containerId){
        try {
            DockerClientService.getDocker().startContainer(containerId);
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
