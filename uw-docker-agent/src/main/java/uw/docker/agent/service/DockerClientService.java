package uw.docker.agent.service;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;

/**
 * dockerClient服务。
 * 基于简化编程考虑，直接static化。
 * @author axeon
 */
public class DockerClientService {


    /**
     * docker client实例。
     */
    private static DefaultDockerClient docker;

    /**
     * 初始化DockerClient。
     */
    static {
        try {
            docker =  DefaultDockerClient.fromEnv().build();
        } catch (DockerCertificateException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获得docker实例。
     * @return
     */
    public static DefaultDockerClient getDocker() {
        return docker;
    }

    /**
     * 拉取镜像。
     * @param image
     * @throws DockerException
     * @throws InterruptedException
     */
    public static void pull(String image) throws DockerException, InterruptedException {
        docker.pull(image);
    }


}
