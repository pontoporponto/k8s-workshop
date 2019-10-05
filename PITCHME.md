## First Steps on Kubernetes
#### Mindera Open Day, 19 Oct

@snap[south-east text-06]
César Rodrigues
Senior Backend Developer
@pontoporponto
@snapend

+++

## What is Kubernetes? (K8s)
#### <a href="http://kubernetes.io" target="_blank">kubernetes.io</a>

+++

@snap[north-west span-85]
#### What is Kubernetes?
@snapend

@snap[west span-85]
- Container Orchestration Tool
- Kubernetes Objects
- Declarative Intent
- Managed vs. Serviced
@snapend

+++

@snap[north-west span-85]
#### Topics Covered
@snapend

@snap[west span-85]
- Kubernetes API / kubectl
- Pod
- Deployment (ReplicaSet / Probes)
- Horizontal Pod Autoscaler
- DaemonSet
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

@snap[span-100]
@code[yaml zoom-12 code-max](simple-container-pod.yaml)
@snapend

+++

bajoras