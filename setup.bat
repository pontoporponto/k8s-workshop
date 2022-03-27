kubectl config set-cluster workshop --embed-certs --certificate-authority=workshop.ca.crt --server=https://34.65.48.127
kubectl config set-credentials workshop --auth-provider=gcp
export GOOGLE_APPLICATION_CREDENTIALS=workshop.json
kubectl config set-context workshop --cluster=workshop --user=workshop --namespace=$1
kubectl config use-context workshop
kubectl get nodes