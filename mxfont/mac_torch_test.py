import torch

def check_mps_available():
    if torch.backends.mps.is_available():
        print("MPS is available")
    else:
        print("MPS is not available")

check_mps_available()