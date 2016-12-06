package anh.tv;

public class GA {

    // thông số
    private static final double mutationRate = 0.015; //tỉ lệ đột biến
    private static final int tournamentSize = 5;      // tỉ lệ đấu
    private static final boolean elitism = true;

     // cạnh tranh trên 1 thế hệ 
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // giữ cá thể tốt nhất
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Tạo thêm dân từ dân số hiện tại
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // chọn bố mẹ
            Tour parent1 = tournamentSelection(pop);
            Tour parent2 = tournamentSelection(pop);
            // tạo dân
            Tour child = crossover(parent1, parent2);
            // Thêm dân mới
            newPopulation.saveTour(i, child);
        }

        // biến đổi để thêm 1 số vật liệu di truyền mới 
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }

        return newPopulation;
    }

    // áp dụng lai ghép để tạo ra cá thể con
    public static Tour crossover(Tour parent1, Tour parent2) {
        Tour child = new Tour();

        // lấy điểm đầu và cuối của bọ gen cá thể cha
        int startPos = (int) (Math.random() * parent1.tourSize());
        int endPos = (int) (Math.random() * parent1.tourSize());

        // thêm gen đó vào cá thể con
        for (int i = 0; i < child.tourSize(); i++) {
            // nếu điểm đầu bé hơn điểm cuối
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } // điểm đầu lớn hơn điểm cuối
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // vòng qua tour của cá thể mẹ
        for (int i = 0; i < parent2.tourSize(); i++) {
            // nếu cá thể con k có thành phố thêm
            if (!child.containsCity(parent2.getCity(i))) {
                // tìm vị trí có gen bị trùng và thêm gen mới
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Biến đổi sử dụng đột biến
    private static void mutate(Tour tour) {
        for(int tourPos1=0; tourPos1 < tour.tourSize(); tourPos1++){
            // áp dụng tỉ lệ đột biến
            if(Math.random() < mutationRate){
                int tourPos2 = (int) (tour.tourSize() * Math.random());  // lấy vị trí ngẫu nhiên thứ 2 trong tour 
                // lấy 2 thành phố ở 2 vị trí ngẫu nhiên và đổi chỗ cho nhau
                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);
                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }
    }

    // chọn tour để đấu
    private static Tour tournamentSelection(Population pop) {
        // tạo 1 giải đấu 
        Population tournament = new Population(tournamentSize, false);
        // với mỗi vị trí chọn ngẫu nhiên 1 tour để tham gia đấu
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        // lấy tour thích hợp nhất
        Tour fittest = tournament.getFittest();
        return fittest;
    }
}
