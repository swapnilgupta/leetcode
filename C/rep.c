#include <stdio.h>
#include <stdlib.h>

void printRepeating(int a[], int n) {
  int i;
  for (i = 0; i < n; i++) {

    if (a[abs(a[i])] > 0)
      a[abs(a[i])] = -a[abs(a[i])];
    else
      printf("%d ", abs(a[i]));
  }
}

int main() {
  int arr[] = {1, 2, 3, 1, 3, 6, 6};
  int arr_size = sizeof(arr) / sizeof(arr[0]);
  printRepeating(arr, arr_size);
  getchar();
  return 0;
}
