#include <iostream>
using namespace std;

void sortInWave(int a[], int n) {
  int temp;
  for (int i = 0; i < n - 1; i = i + 2) {
    if (i > 0 && a[i] < a[i - 1]) {
      temp = a[i];
      a[i] = a[i - 1];
      a[i - 1] = temp;
    }
    if (i < n - 1 && a[i] < a[i + 1]) {
      temp = a[i];
      a[i] = a[i + 1];
      a[i + 1] = temp;
    }
  }
}

int main() {
  int arr[] = {10, 90, 49, 2, 1, 5, 23};
  int n = sizeof(arr) / sizeof(arr[0]);
  sortInWave(arr, n);
  cout << "Sorted array \n";
  for (int i = 0; i < n; i++)
    cout << arr[i] << " ";
  return 0;
}
