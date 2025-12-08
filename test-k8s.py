#!/usr/bin/env python3
import subprocess
import time

print("Cleaning up old Kubernetes resources...")
subprocess.run(["kubectl", "delete", "deployment", "product-deployment", "product-composite-deployment", "recommendation-deployment", "review-deployment", "mongodb", "mysql", "zookeeper", "kafka", "--ignore-not-found=true"])
subprocess.run(["kubectl", "delete", "svc", "product-composite-service", "product", "recommendation", "review", "mongodb", "mysql", "zookeeper", "kafka", "--ignore-not-found=true"])

print("\n✓ Cleanup done!\n")

print("Applying Kubernetes configuration...")
subprocess.run(["kubectl", "apply", "-f", "kubernetes.yaml"])

print("\n✓ Configuration applied!\n")
time.sleep(3)

print("Getting pods...")
subprocess.run(["kubectl", "get", "pods"])

print("\nGetting services...")
subprocess.run(["kubectl", "get", "svc"])

print("\nWaiting for pods to be ready...")
for i in range(30):
    result = subprocess.run(["kubectl", "get", "pods", "-o", "jsonpath={.items[*].status.containerStatuses[*].ready}"], capture_output=True, text=True)
    if result.stdout.count("true") >= 4:
        print("✓ All pods ready!")
        break
    print(f"Waiting... ({i+1}/30)")
    time.sleep(2)

print("\nWaiting for services to be fully started...")
print("Waiting 45 seconds for Spring Boot apps to start...")
time.sleep(45)

print("\nCreating product composite with ID 99...")
subprocess.run(["curl", "-X", "POST", "http://localhost:30080/product-composite", "-H", "Content-Type: application/json", "-d", '{"product":{"productId":99,"name":"Test Product","weight":1500},"reviews":[{"productId":99,"reviewId":1,"author":"Tester","subject":"Works","content":"K8s test"}],"recommendations":[{"productId":99,"recommendationId":1,"author":"Admin","rate":5,"content":"Perfect"}]}'])

print("\n\nTesting the API - Get product 99...")
subprocess.run(["curl", "http://localhost:30080/product-composite/99"])

print("\n\n✓ K8s deployment done!")
