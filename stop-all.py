#!/usr/bin/env python3
"""
Script pour arrêter tous les services Docker Compose
Compatible Windows, macOS et Linux
"""
import subprocess
import sys

def main():
    print("=" * 50)
    print("Stopping All Services...")
    print("=" * 50)
    
    try:
        # Arrêter les services avec Docker Compose
        subprocess.run(["docker", "compose", "down"], check=True)
        
        print()
        print("=" * 50)
        print("All services stopped!")
        print("=" * 50)
        
    except subprocess.CalledProcessError as e:
        print(f"Error: Failed to stop services: {e}", file=sys.stderr)
        sys.exit(1)
    except FileNotFoundError:
        print("Error: Docker not found. Please install Docker.", file=sys.stderr)
        sys.exit(1)

if __name__ == "__main__":
    main()
