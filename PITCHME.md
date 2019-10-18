## First Steps on Kubernetes
#### Mindera Tech Day, 19 Oct

@snap[south-east text-06]
César Rodrigues
Senior Backend Developer
@pontoporponto
@snapend

+++

## What is Kubernetes? (K8s)
#### <a href="http://kubernetes.io" target="_blank">kubernetes.io</a>

+++

@snap[north-west span-40]
@box[bg-orange text-white rounded](Container Orchestration Tool)
@snapend

@snap[north-east span-40]
@box[bg-orange text-white rounded](Kubernetes Objects)
@snapend

@snap[west span-40]
@box[bg-orange text-white rounded](1 Master / N Nodes)
@snapend

@snap[south-west span-40]
@box[bg-orange text-white rounded](Declarative Intent)
@snapend

@snap[south-east span-40]
@box[bg-orange text-white rounded](Managed vs. Serviced)
@snapend

+++

@snap[north-west span-85]
#### Topics Covered
@snapend

@snap[north-west span-40]
@box[bg-orange text-white rounded](Kubernetes API / kubectl)
@snapend

@snap[north-east span-40]
@box[bg-orange text-white rounded](Pod)
@snapend

@snap[west span-40]
@box[bg-orange text-white rounded](Deployment (ReplicaSet / Probes))
@snapend

@snap[east span-40]
@box[bg-orange text-white rounded](Services / Networking)
@snapend

@snap[south-west span-40]
@box[bg-orange text-white rounded](Horizontal Pod Autoscaler)
@snapend

@snap[south-east span-40]
@box[bg-orange text-white rounded](DaemonSet)
@snapend

+++
  
```Bash
kubectl config set-cluster workshop \
    --insecure-skip-tls-verify=true --server=
kubectl config set-credentials participant \
    --username=admin --password=
kubectl config set-context workshop \
    --cluster=workshop --user=participant
kubectl config use-context workshop
```
@[1,2](Define Cluster; Set proper master IP)
@[3,4](Define credentials; Basic authentication for user admin)
@[5,6](Context definion; Pair cluster and credentials)
@[7](Start Using context)

+++

## Pod
#### 1 Pod == 1 Container

@box[bg-gray fragment span-100](kubectl apply -f simple-container-pod.yaml)

+++

@snap[span-100]
@code[yaml zoom-12 code-max](simple-container-pod.yaml)
@snapend

@[3,4](Define Pod name)
@[6,7,8](Specify container)

+++

## Deployment
#### Pod Lifecycle Management

@box[bg-gray fragment span-100](kubectl apply -f simple-service-deployment.yaml)

+++

@snap[span-100]
@code[yaml zoom-11 code-max](simple-service-deployment.yaml)
@snapend

+++

@snap[north]
## Probes
@ul 
- liveness
- readiness
- Exec
- TCP
- HTTP
- periodSeconds
- timeoutSeconds
- failureThreshold
- successThreshold
- initialDelaySeconds
@ulend
@snapend

+++

```Yaml
livenessProbe:
  httpGet:
    path: /workshop/simple
    port: 8080
  periodSeconds: 10
  timeoutSeconds: 5
  failureThreshold: 2
```

+++

## ReplicaSet

@box[bg-gray fragment span-100](replicas: 2)
@box[bg-gray fragment span-100](kubectl scale deployment \<br />simple-service-workshop --replicas=2)

+++

## Rollout Update Policy
@ul
- Recreate
- RollingUpdate
    - maxSurge
    - maxUnavailable
@ulend

+++

![RollingUpdate](https://www.exoscale.com/static/syslog/2019-02-07-kubernetes-zero-downtime-deployment/maxsurge1-maxunavailable1.svg)

@snap[source text-04]
Source: https://www.exoscale.com/syslog/kubernetes-zero-downtime-deployment/
@snapend

+++

```Yaml
strategy:
  rollingUpdate:
    maxUnavailable: 0
    maxSurge: 0
```

+++

## Service
@ul
- ClusterIP
- NodePort
- LoadBalancer
@ulend

+++

## Service

@box[bg-gray fragment span-100](kubectl apply -f simple-service-service.yaml)
@box[bg-gray fragment span-100](kubectl get nodes)
@box[bg-gray fragment span-100](kubectl describe nodes XXXXXX)

+++

@snap[span-100]
@code[yaml zoom-12 code-max](simple-service-service.yaml)
@snapend