#include <stdio.h>

int equilibrium(int arr[], int n) {
  int leftsum = 0;
  int rightsum = 0;
  int i;
  for (i = 0; i < n; i++)
    rightsum += arr[i];

  for (i = 0; i < n; i++) {
    rightsum -= arr[i];
    if (leftsum == rightsum)
      return i;
    leftsum += arr[i];
  }
  return -1;
}

int main() {
  int arr[] = {-7, 1, 5, 2, -4, 3, 0};
  int arr_size = sizeof(arr) / sizeof(arr[0]);
  printf("%d\n", equilibrium(arr, arr_size));

  getchar();
  return 0;
}
