#!/usr/bin/env python3
import subprocess

print("Stopping all services...")
subprocess.run(["docker", "compose", "down"])
print("✓ Services stopped!")
