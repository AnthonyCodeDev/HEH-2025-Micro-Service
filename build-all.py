#!/usr/bin/env python3
"""
Script pour compiler tous les services
Compatible Windows, macOS et Linux
"""
import subprocess
import sys
import platform

def main():
    print("=" * 50)
    print("Building All Services...")
    print("=" * 50)
    
    try:
        # Déterminer le script Gradle à utiliser selon l'OS
        is_windows = platform.system() == "Windows"
        gradle_cmd = "./gradlew.bat" if is_windows else "./gradlew"
        
        # Compiler tous les services avec Gradle
        print("Building all services with Gradle...")
        subprocess.run([gradle_cmd, "clean", "build", "-x", "test"], check=True, shell=is_windows)
        
        print()
        print("Building Docker images...")
        subprocess.run(["docker", "compose", "build"], check=True)
        
        print()
        print("=" * 50)
        print("Build completed successfully!")
        print("=" * 50)
        
    except subprocess.CalledProcessError as e:
        print(f"Error: Build failed: {e}", file=sys.stderr)
        sys.exit(1)
    except FileNotFoundError as e:
        print(f"Error: Required tool not found: {e}", file=sys.stderr)
        sys.exit(1)

if __name__ == "__main__":
    main()
