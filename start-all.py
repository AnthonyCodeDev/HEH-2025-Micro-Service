#!/usr/bin/env python3
import subprocess

print("Starting all services...")
subprocess.run(["docker", "compose", "up", "-d"])
print("✓ Services started!")
print("Gateway: http://localhost:8080")
