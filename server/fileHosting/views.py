from django.shortcuts import render
from django.http import JsonResponse

from rest_framework.viewsets import ViewSet
from . import serializers, models

# Create your views here.

class FileViewSet(ViewSet):
    model = models.File
    serializers = serializers.FileSerializer
    
    def create(self, request):
        serializer = self.serializers(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status = 200)
        else:
            return JsonResponse(serializer.errors, status = 400)
    