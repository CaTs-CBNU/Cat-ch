from django.db import models

# Create your models here.

class User(models.Model):
    token = models.CharField(max_length = 255, primary_key = True)
    name = models.CharField(max_length = 255)