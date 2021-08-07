// Reference: https://www.profit4cloud.nl/blog/deploying-a-spring-boot-app-to-fargate-with-the-aws-cdk/
import * as cdk from "@aws-cdk/core";
import * as ecsPatterns from "@aws-cdk/aws-ecs-patterns";
import * as ecs from "@aws-cdk/aws-ecs";
import * as ec2 from "@aws-cdk/aws-ec2";
import { DockerImageAsset } from "@aws-cdk/aws-ecr-assets";
import * as path from "path";
import { SecurityGroup } from "@aws-cdk/aws-ec2";

const DOCKER_BASE_DIR = "../../../";

export class ChaosSpringbootStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

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
          memoryLimitMiB: 1024,
          cpu: 512,
          taskImageOptions: {
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
  }
}
