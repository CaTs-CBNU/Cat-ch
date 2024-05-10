from django.shortcuts import render
from django.http import JsonResponse
from rest_framework.viewsets import ViewSet

from . import serializers, models
# Create your views here.


class UserViewSet(ViewSet):
    model = models.User
    serializer = serializers.UserSerializer
    def create(self, request):
        serializer = self.serializer(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status = 200)
        else:
            return JsonResponse(serializer.errors, status = 400)
    def retrieve(self, request, pk = None):
        try:
            instance = self.model.objects.get(token = pk)
        except self.model.DoesNotExist:
            return JsonResponse("Object does not exist", status = 400)
        serializer = self.serializer(instance, request.data)
        if serializer.is_valid():
            serializer.save()
            JsonResponse(self.serializer(instance).data, status = 200)
        else:
            JsonResponse(serializer.errors, status = 400)