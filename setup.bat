kubectl config set-cluster workshop --insecure-skip-tls-verify=true --server=https://$1
kubectl config set-credentials participant --username=admin --password=$2
kubectl config set-context workshop --cluster=workshop --user=participant
kubectl config use-context workshop