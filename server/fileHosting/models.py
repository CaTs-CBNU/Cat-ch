from django.db import models

from userInfo.models import User
# Create your models here.

class File(models.Model):
    file_name = models.CharField(max_length=255, primary_key=True)
    file = models.FileField()
    upload_user = models.ForeignKey(User, on_delete = models.CASCADE, null = True)