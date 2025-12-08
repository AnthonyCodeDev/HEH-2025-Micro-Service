#!/usr/bin/env python3
import subprocess
import time

def curl(method, url, data=None):
    cmd = ["curl", "-X", method, url]
    if data:
        cmd.extend(["-H", "Content-Type: application/json", "-d", data])
    result = subprocess.run(cmd, capture_output=True, text=True)
    print(result.stdout)
    if result.stderr:
        print(result.stderr)

print("Testing API on Kubernetes (via NodePort 30080)...\n")

print("1. Create composite product")
curl("POST", "http://localhost:30080/product-composite", '{"product":{"productId":1,"name":"Laptop","weight":2000},"reviews":[{"productId":1,"reviewId":1,"author":"Bob","subject":"Good","content":"Nice"}],"recommendations":[{"productId":1,"recommendationId":1,"author":"Alice","rate":5,"content":"Great"}]}')
time.sleep(2)

print("\n2. Get composite")
curl("GET", "http://localhost:30080/product-composite/1")

print("\n3. Delete composite")
curl("DELETE", "http://localhost:30080/product-composite/1")

print("\n✓ K8s tests done!")
