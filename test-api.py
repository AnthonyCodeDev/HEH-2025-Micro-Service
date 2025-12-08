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

print("Testing API...\n")

print("1. Create product")
curl("POST", "http://localhost:7002/product", '{"productId":1,"name":"Laptop","weight":2000}')
time.sleep(1)

print("\n2. Create recommendation")
curl("POST", "http://localhost:7003/recommendations", '{"productId":1,"recommendationId":1,"author":"Alice","rate":5,"content":"Great"}')
time.sleep(1)

print("\n3. Create review")
curl("POST", "http://localhost:7004/review", '{"productId":1,"reviewId":1,"author":"Bob","subject":"Good","content":"Nice"}')
time.sleep(1)

print("\n4. Get composite")
curl("GET", "http://localhost:7001/product-composite/1")
print("\n✓ Tests done!")
