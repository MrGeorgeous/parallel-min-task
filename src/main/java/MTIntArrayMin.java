import java.util.concurrent.atomic.AtomicIntegerArray;

public class MTIntArrayMin implements IMinFinder<Integer> {

    /**
     * Количество потоков, которые будут использоваться при работе алгоритма
     */
    private final static int THREADS_N = Runtime.getRuntime().availableProcessors();

    /**
     * Массив, минимум среди элементов которого мы хотим найти
     */
    private final int[] array;

    /**
     * Сюда необходимо записать результат каждого потока с помощью:
     * <pre>
     *     int threadId = 1; // номер потока от 0 до THREADS_N - 1
     *     int result = 5; // минимум, среди элементов, которые поток смотрел
     *     results.set(threadId, result);
     * </pre>
     */
    private AtomicIntegerArray results = new AtomicIntegerArray(THREADS_N);

    public MTIntArrayMin(int[] array) {
        this.array = array;
    }

    /**
     * Находит минимум среди элементов массива array, используя многопоточную реализацию
     * @return целое число, являющееся минимальным в массиве
     */
    @Override
    public Integer get() {

        // TODO: если размер массива меньше количества потока, использовать однопоточную реализацию get(0, array.length)

        Thread[] threads = new Thread[THREADS_N];
        for (int i = 0; i < THREADS_N; i++) {
            final int threadId = i;
            final int startIndex;
            // TODO: определить индекс первого элемента для i-ого потока
            final int n;
            // TODO: определить количество элементов, которые i-ый поток должен просмотреть, начиная с индекса startIndex
            threads[i] = new Thread(() -> {
                // TODO: запишите сюда, код, который должен исполниться на i-ом потоке
                // это отдельная программа, которая исполняется независимо от остальных
                // вы должны вызвать метод get(startIndex, n) и записать результат в results
            });
        }

        try {
            for (int i = 0; i < THREADS_N; i++) {
                // TODO: запустите i-ый поток на исполнение
            }
            for (int i = 0; i < THREADS_N; i++) {
                // TODO: дождитесь завершения i-ого потока
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // TODO: найти минимум среди результатов, которые записаны в results, и вернуть в качестве ответа
        // обратиться к i-ому элементу массива results можно с помощью results.get(i)

        throw new UnsupportedOperationException("Метод до конца не реализован");
    }

    /**
     * Найти минимум в подмассиве (классическая однопоточная реализация)
     * @param startIndex индекс массива array, начиная с которого нужно начинать искать минимальный элемент
     * @param quantity количество элементов, начиная с startIndex, среди которых надо найти минимум
     * @return миниальный элемент среди указанного подмассива
     */
    private Integer get(int startIndex, int quantity) {
        assert (startIndex >= 0);
        assert (quantity >= 0);
        throw new UnsupportedOperationException("Метод до конца не реализован");
    }

}
