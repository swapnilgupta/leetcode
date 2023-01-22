#include <stdio.h>

void printUnsorted(int a[], int n) {
  int s, e;
  int i;
  for (i = 0; i < n - 1; i++) {
    if (a[i + 1] < a[i])
      break;
  }
  s = i;
  for (i = n - 1; i > 0; i--) {
    if (a[i - 1] > a[i])
      break;
  }
  e = i;

  // check that unsorted array if sorted really the whole array becomes sorted.
  int max = a[s], min = a[s];
  for (i = s; i <= e; i++) {
    if (min > a[i])
      min = a[i];
    if (max < a[i])
      max = a[i];
  }
  printf("%d %d", min, max);
  int left, right;
  for (i = 0; i <= s; i++)
    if (min < a[i])
      break;
  left = i;

  for (i = n - 1; i >= e; i--)
    if (max > a[i])
      break;
  right = i;

  printf("left: %d right: %d", left, right);
}

int main() {
  int arr[] = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
  int arr_size = sizeof(arr) / sizeof(arr[0]);
  printUnsorted(arr, arr_size);
  getchar();
  return 0;
}
