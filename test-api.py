#!/usr/bin/env python3
"""
Script de test de la couche de persistance
Compatible Windows, macOS et Linux
"""
import subprocess
import sys
import time
import json

def run_curl(method, url, data=None):
    """Execute une requête curl"""
    cmd = ["curl", "-X", method, url]
    if data:
        cmd.extend(["-H", "Content-Type: application/json", "-d", json.dumps(data)])
    
    try:
        result = subprocess.run(cmd, capture_output=True, text=True)
        print(result.stdout)
        if result.stderr:
            print(result.stderr, file=sys.stderr)
    except FileNotFoundError:
        print("Error: curl not found. Please install curl.", file=sys.stderr)
        sys.exit(1)

def main():
    print("=" * 50)
    print("Test de la couche de persistance")
    print("=" * 50)
    print()
    
    # 1. Création d'un produit
    print("1. Creation d'un produit...")
    run_curl("POST", "http://localhost:7002/product", {
        "productId": 1,
        "name": "Laptop Dell",
        "weight": 2000
    })
    print("\n")
    time.sleep(2)
    
    # 2. Création d'une recommendation
    print("2. Creation d'une recommendation...")
    run_curl("POST", "http://localhost:7003/recommendations", {
        "productId": 1,
        "recommendationId": 1,
        "author": "Alice",
        "rate": 5,
        "content": "Excellent produit"
    })
    print("\n")
    time.sleep(2)
    
    # 3. Création d'un review
    print("3. Creation d'un review...")
    run_curl("POST", "http://localhost:7004/review", {
        "productId": 1,
        "reviewId": 1,
        "author": "Bob",
        "subject": "Tres bon",
        "content": "Je suis tres satisfait"
    })
    print("\n")
    time.sleep(2)
    
    # 4. Récupération du produit
    print("4. Recuperation du produit...")
    run_curl("GET", "http://localhost:7002/product/1")
    print("\n")
    
    # 5. Récupération des recommendations
    print("5. Recuperation des recommendations...")
    run_curl("GET", "http://localhost:7003/recommendations/1")
    print("\n")
    
    # 6. Récupération des reviews
    print("6. Recuperation des reviews...")
    run_curl("GET", "http://localhost:7004/review?productId=1")
    print("\n")
    
    # 7. Récupération du produit composite
    print("7. Recuperation du produit composite...")
    run_curl("GET", "http://localhost:7001/product-composite/1")
    print("\n")
    
    print("=" * 50)
    print("Tests termines!")
    print("=" * 50)

if __name__ == "__main__":
    main()
