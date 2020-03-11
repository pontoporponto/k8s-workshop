## First Steps on Kubernetes
#### Geek Girls PT - Mindera, 11 Jan

@snap[south-west text-06]
Wi-Fi: Mind Da Guest
Password: youarewelcome
@snapend

@snap[south-east text-06]
César Rodrigues
Full Cycle Developer@Mindera
@pontoporponto
@snapend

+++

## Mindera
#### We use technology to build products we are proud of, with people we love
#### <a href="https://mindera.com/people-and-culture/" target="_blank">Handbook</a>

+++

## What is Kubernetes? (K8s)
#### <a href="http://kubernetes.io" target="_blank">kubernetes.io</a>

+++

@snap[north-west span-40]
@box[bg-orange text-white rounded](Container Orchestration Tool)
@snapend

@snap[north-east span-40]
@box[bg-orange text-white rounded](1 Master / N Nodes)
@snapend

@snap[west span-40]
@box[bg-orange text-white rounded](Kubernetes Objects)
@snapend

@snap[east span-40]
@box[bg-orange text-white rounded](Declarative Intent)
@snapend

@snap[south-west span-40]
@box[bg-orange text-white rounded](Extensible)
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

## Setup

@box[bg-gray fragment span-100](Install kubectl (Homebrew, chocolatey, apt-get, download))
@box[bg-gray fragment span-100](kubectl autocomplete - Google it, please!)
@box[bg-gray fragment span-100](https://github.com/pontoporponto/k8s-workshop)
@box[bg-gray fragment span-100](./setup.sh IP PASSWORD)
@box[bg-gray fragment span-100](kubectl get nodes)

+++

## Pod
#### 1 Pod == 1 Container

@box[bg-gray fragment span-100](kubectl apply -f simple-container-pod.yaml)

+++

```Yaml
apiVersion: v1
kind: Pod
metadata:
  name: simple-container-workshop
spec:
  containers:
  - name: simple-container
    image: gcr.io/pontoporponto/simple-container:latest
    resources:
      limits:
        cpu: 250m
      requests:
        memory: 250Mi
```

@[3,4](Define Pod name)
@[6,7,8](Specify container)
@[9,10,11,12,13](Resources management)

+++

## Deployment
#### Pod Lifecycle Management

@box[bg-gray fragment span-100](kubectl apply -f simple-service-deployment.yaml)
@box[bg-gray fragment span-100](kubectl port-forward simple-service-workshop-XXXXXX 8080)

+++

```Yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: simple-service-workshop
spec:
  selector:
    matchLabels:
      app: simple-service
  template:
    metadata:
      labels:
        app: simple-service
    spec:
      containers:
      - name: simple-container
        image: gcr.io/pontoporponto/simple-service:latest
        resources:
          limits:
            memory: 50M
```

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
@box[bg-gray fragment span-100](kubectl exec -it simple-container-workshop sh)
@box[bg-gray fragment span-100](curl -X GET http://service-endpoint/workshop/simple)

+++

```Yaml
apiVersion: v1
kind: Service
metadata:
  name: service-endpoint
spec:
  type: ClusterIP
  selector:
    app: simple-service
  ports:
   - port: 80
     name: workshop
     targetPort: 8080
```

+++

```Yaml
nodePort: 32000
###########
###########
image: gcr.io/pontoporponto/simple-client:latest
###########
###########
env:
  - name: GROUP
    value: "workshop-1"  
```

+++

## Horizontal Pod Autoscaler

@box[bg-gray fragment span-100](kubectl apply -f heavy-stress-deployment.yaml)
@box[bg-gray fragment span-100](kubectl autoscale deployment heavy-service-workshop --cpu-percent=30 --min=1 --max=10)
@box[bg-gray fragment span-100](curl -X GET http://localhost:8080/workshop/stress?load=0.5&duration=120)

+++


![Feedback](formQrCode.png)

<a href="https://forms.gle/qcCAusi248K3fCg39">Feedback</a>