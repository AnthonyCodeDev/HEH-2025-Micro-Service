#!/usr/bin/env python3
import subprocess
import time

def curl(method, url, data=None):
    cmd = ["curl", "-s", "-X", method, url]
    if data:
        cmd.extend(["-H", "Content-Type: application/json", "-d", data])
    subprocess.run(cmd)

print("Testing composite service...\n")

print("1. Create composite")
curl("POST", "http://localhost:7001/product-composite", '{"product":{"productId":2,"name":"MacBook","weight":1800},"reviews":[{"productId":2,"reviewId":1,"author":"Jean","subject":"Great","content":"Nice"}],"recommendations":[{"productId":2,"recommendationId":1,"author":"Marie","rate":5,"content":"Good"}]}')
time.sleep(2)

print("\n2. Get composite")
curl("GET", "http://localhost:7001/product-composite/2")
time.sleep(1)

print("\n3. Delete composite")
curl("DELETE", "http://localhost:7001/product-composite/2")
print("\n\n✓ Tests done!")
