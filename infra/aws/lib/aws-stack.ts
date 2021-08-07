// Reference: https://www.profit4cloud.nl/blog/deploying-a-spring-boot-app-to-fargate-with-the-aws-cdk/
import * as cdk from "@aws-cdk/core";
import * as ecsPatterns from "@aws-cdk/aws-ecs-patterns";
import * as ecs from "@aws-cdk/aws-ecs";
import * as ec2 from "@aws-cdk/aws-ec2";
// import * as ecrdeploy from "cdk-ecr-deployment";
import { DockerImageAsset } from "@aws-cdk/aws-ecr-assets";
import * as path from "path";
import { SecurityGroup } from "@aws-cdk/aws-ec2";

const DOCKER_BASE_DIR = "../../../";
// const ACCOUNT_ID = "303176307655";
// const DOCKER_IMAGE_NAME = "chaostoolkit-spring";

export class ChaosSpringbootStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    // const image = new DockerImageAsset(this, "ChaostoolkitSpringDockerImage", {
    //   directory: path.join(__dirname, DOCKER_BASE_DIR),
    // });

    // new ecrdeploy.ECRDeployment(
    //   this,
    //   "ChaostoolkitSpringbootDeployDockerImage",
    //   {
    //     src: new ecrdeploy.DockerImageName(image.imageUri),
    //     dest: new ecrdeploy.DockerImageName(
    //       `${ACCOUNT_ID}.dkr.ecr.us-west-2.amazonaws.com/${DOCKER_IMAGE_NAME}:v1`
    //     ),
    //   }
    // );

    // const vpc = ec2.Vpc.fromLookup(this, "ChaostoolkitSpringbootVPC", {
    //   isDefault: true,
    // });

    const vpc = new ec2.Vpc(this, "ChaostoolkitSpringbootVPC");

    const cluster = new ecs.Cluster(this, "ChaostoolkitSpringbootCluster", {
      vpc,
    });

    const loadBalancedFargateService =
      new ecsPatterns.ApplicationLoadBalancedFargateService(
        this,
        "ChaostoolkitSpringbootService",
        {
          cluster,
          desiredCount: 2,
          memoryLimitMiB: 1024,
          cpu: 512,
          taskImageOptions: {
            // image: ecs.ContainerImage.fromRegistry("amazon/amazon-ecs-sample"),
            // image: ecs.ContainerImage.fromEcrRepository(image.repository),
            image: ecs.ContainerImage.fromAsset(path.join(__dirname, DOCKER_BASE_DIR)),
            containerPort: 8080,
          },
          publicLoadBalancer: true,
          assignPublicIp: false,
        }
      );

    loadBalancedFargateService.targetGroup.configureHealthCheck({
      healthyHttpCodes: "200",
      path: "/actuator/health",
      port: "8080",
    });
    // The code that defines your stack goes here
  }
}
