#!/usr/bin/env python3
import subprocess

print("Stopping Docker Compose services...")
subprocess.run(["docker", "compose", "down"])
print("✓ Docker Compose stopped!")

print("\nStopping Kubernetes deployments...")
subprocess.run(["kubectl", "delete", "-f", "kubernetes.yaml", "--ignore-not-found=true"])
print("✓ Kubernetes stopped!")

print("\n✓ All services stopped!")
