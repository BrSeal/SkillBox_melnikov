public class Loader
{
	public static void main(String[] args) {
		float[] temperature = new float[30];
		
		fillArray(temperature);
		
		System.out.printf("Средняя температура по больнице: %.1f\nПациентов с нормальной температурой: %d", average(temperature), healthyCount(temperature));
	}
	
	private static void fillArray(float[] data) {
		for (int i = 0; i < 30; i++) {
			float temper = (float) (Math.random() * 8 + 32);
			data[i] = (float) Math.round(temper * 10) / 10;
			System.out.println(data[i]);
		}
	}
	
	private static float average(float[] data) {
		float sum = 0;
		for (float i : data) sum += i;
		return sum / data.length;
	}
	
	private static int healthyCount(float[] data) {
		int healthyCount = 0;
		
		for (float i : data) {
			if (i > 36.2 && i < 36.9) {
				healthyCount++;
			}
		}
		return healthyCount;
	}
}
