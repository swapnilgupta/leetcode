package arrays;

public class CanPlaceFlowers {

	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		for (int i = 0; i < flowerbed.length; ++i) {
			if (flowerbed[i] == 0) {
				boolean prev = i == 0 || flowerbed[i - 1] == 0;
				boolean next = i == flowerbed.length - 1 || flowerbed[i + 1] == 0;
				if (prev && next) {
					--n;
					flowerbed[i] = 1;
					if (n == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// driver code for 1,0,0,0,0,1 nad n = 2

	public static void main(String[] args) {
		CanPlaceFlowers cpf = new CanPlaceFlowers();
		int[] flowerbed = {1, 0, 0, 0, 0, 1};
		int n = 2;
		System.out.println(cpf.canPlaceFlowers(flowerbed, n));
	}
}
