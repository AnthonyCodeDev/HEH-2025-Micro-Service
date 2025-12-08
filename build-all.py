#!/usr/bin/env python3
import subprocess
import platform

print("Building all services...")
gradle = "gradlew.bat" if platform.system() == "Windows" else "./gradlew"
subprocess.run([gradle, "clean", "build", "-x", "test"])
subprocess.run(["docker", "compose", "build"])
print("✓ Build completed!")
