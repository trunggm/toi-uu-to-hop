package kiennv.bee;
import java.util.Random;
/**
 *
 * @author Kien
 */
public class Hive {
    public class Bee{
            public int status; // 0 = inactive, 1 = active, 2 = scout
            public int[] memoryMatrix; // problem-specific. a path of cities.
            public double measureOfQuality; // smaller values are better. total distance of path.
            public int numberOfVisits;

            public Bee(int status, int[] memoryMatrix, double measureOfQuality, int numberOfVisits)
            {
                this.status = status;
                this.memoryMatrix = new int[memoryMatrix.length];
                System.arraycopy(memoryMatrix, 0, this.memoryMatrix, 0, memoryMatrix.length);
                this.measureOfQuality = measureOfQuality;
                this.numberOfVisits = numberOfVisits;
            }

            public String toString()
            {
                String s = "";
                s += "Status = " + this.status + "\n";
                s += " Memory = " + "\n";
                for (int i = 0; i < this.memoryMatrix.length - 1; ++i)
                    s += this.memoryMatrix[i] + "->";
                
                s += this.memoryMatrix[this.memoryMatrix.length - 1] + "\n";
                s += " Quality = " + Double.toString(this.measureOfQuality) + "\n" ;
                s += " Number visits = " + this.numberOfVisits;
                return s;
            }
        }
        public Random random = null; // multipurpose

        public CitiesData citiesData; // this is the problem-specific data we want to optimize

        public int totalNumberBees; // mostly for readability in the object constructor call
        public int numberInactive;
        public int numberActive;
        public int numberScout;

        public int maxNumberCycles; // one cycle represents an action by all bees in the hive
        //public int maxCyclesWithNoImprovement; // deprecated

        public int maxNumberVisits; // max number of times bee will visit a given food source without finding a better neighbor
        public double probPersuasion = 0.95; // probability inactive bee is persuaded by better waggle solution
        public double probMistake = 0.01; // probability an active bee will reject a better neighbor food source OR accept worse neighbor food source

        public Bee[] bees;
        public int[] bestMemoryMatrix; // problem-specific
        public double bestMeasureOfQuality;
        public int[] indexesOfInactiveBees; // contains indexes into the bees array

        public String toString()
        {
            String s = "";
            s += "Best path found: ";
            for (int i = 0; i < this.bestMemoryMatrix.length-1; ++i)
                s += this.bestMemoryMatrix[i] + "->";          
            s += this.bestMemoryMatrix[this.bestMemoryMatrix.length-1] + "->" +this.bestMemoryMatrix[0]+ "\n";

            s += "Path quality:    ";
            if (bestMeasureOfQuality < 10000.0)
                s += Double.toString(bestMeasureOfQuality)+"km" + "\n" ;
            else
                s += Double.toString(bestMeasureOfQuality) +"km" + "\n" ;
            return s;
        }
        public Hive(int totalNumberBees, int numberInactive, int numberActive, int numberScout, int maxNumberVisits,
          int maxNumberCycles, CitiesData citiesData)
        {
            random = new Random(0);

            this.totalNumberBees = totalNumberBees;
            this.numberInactive = numberInactive;
            this.numberActive = numberActive;
            this.numberScout = numberScout;
            this.maxNumberVisits = maxNumberVisits;
            this.maxNumberCycles = maxNumberCycles;

            //this.citiesData = new CitiesData(citiesData.cities.Length); // hive's copy of problem-specific data
            this.citiesData = citiesData; // reference to CityData

            // this.probPersuasion & this.probMistake are hard-coded in class definition

            this.bees = new Bee[totalNumberBees];

            // Provides the baseline best random memory matrix
            this.bestMemoryMatrix = GenerateRandomMemoryMatrix(); // alternative initializations are possible
            this.bestMeasureOfQuality = MeasureOfQuality(this.bestMemoryMatrix);

            this.indexesOfInactiveBees = new int[numberInactive]; // indexes of bees which are currently inactive

            for (int i = 0; i < totalNumberBees; ++i) // initialize each bee, and best solution
            {
                int currStatus; // depends on i. need status before we can initialize Bee
                if (i < numberInactive)
                {
                    currStatus = 0; // inactive
                    indexesOfInactiveBees[i] = i; // curr bee is inactive
                }
                else if (i < numberInactive + numberScout)
                {
                    currStatus = 2; // scout
                }
                else
                {
                    currStatus = 1; // active
                }

                int[] randomMemoryMatrix = GenerateRandomMemoryMatrix();
                double mq = MeasureOfQuality(randomMemoryMatrix);
                int numberOfVisits = 0;

                bees[i] = new Bee(currStatus, randomMemoryMatrix, mq, numberOfVisits); // instantiate current bee

                // does this bee have best solution?
                if (bees[i].measureOfQuality < bestMeasureOfQuality) // curr bee is better (< because smaller is better)
                {
                    System.arraycopy(bees[i].memoryMatrix,0, this.bestMemoryMatrix,0, bees[i].memoryMatrix.length);
                    this.bestMeasureOfQuality = bees[i].measureOfQuality;
                }
            } // each bee

        } // TravelingSalesmanHive ctor
        public int[] GenerateRandomMemoryMatrix()
        {
            int[] result = new int[this.citiesData.cities.length]; // // problem-specific
            System.arraycopy(this.citiesData.cities,0, result,0, this.citiesData.cities.length);

            for (int i = 0; i < result.length; i++) // Fisher-Yates (Knuth) shuffle
            {
                int r = random.nextInt(result.length);
                int temp = result[r]; result[r] = result[i]; result[i] = temp;
            }
            return result;
        } // GenerateRandomMemoryMatrix()
        public int[] GenerateNeighborMemoryMatrix(int[] memoryMatrix)
        {
            int[] result = new int[memoryMatrix.length];
            System.arraycopy(memoryMatrix,0, result,0, memoryMatrix.length);

            int ranIndex = 0+random.nextInt(result.length); // [0, Length-1] inclusive; int ranIndex = random.nextInt(0, result.length)//int min = 65;int max = 80;Random r = new Random();int i1 = r.nextInt(max - min + 1) + min;
            int adjIndex;
            if (ranIndex == result.length - 1)
                adjIndex = 0;
            else
                adjIndex = ranIndex + 1;

            int tmp = result[ranIndex];
            result[ranIndex] = result[adjIndex];
            result[adjIndex] = tmp;

            return result;
        } // GenerateNeighborMemoryMatrix()
//        public double MeasureOfQuality1(int[] memoryMatrix)
//        {
//            double answer = 0.0,d=0.0;
//            for (int i = 0; i < memoryMatrix.length-1; i++)
//            {
//                int c1 = memoryMatrix[i];
//                int c2 = memoryMatrix[i + 1];
//                d = this.citiesData.Distance1(c1, c2);          
//                answer += d;
//            }
//            return answer;
//        } // MeasureOfQuality()
        public double MeasureOfQuality(int[] memoryMatrix)
        {
            double answer = 0.0,d=0.0;
            for (int i = 0; i < memoryMatrix.length-1; i++)
            {
                int c1 = memoryMatrix[i];
                int c0 = memoryMatrix[0];
                int end=memoryMatrix.length-1;
                int flag=i;
                int c2 = memoryMatrix[i + 1];
                d = this.citiesData.Distance(c1, c2,end, c0,flag);          
                answer += d;
            }
            return answer;
        } // MeasureOfQuality()
        public void Solve(boolean doProgressBar) throws Exception // find best Traveling Salesman Problem solution
        {
            boolean pb = doProgressBar; // just want a shorter variable
            int numberOfSymbolsToPrint = 10; // 10 units so each symbol is 10.0% progress
            int increment =(int) this.maxNumberCycles / numberOfSymbolsToPrint;
            if (pb) System.out.println("\nEntering SBC Traveling Salesman Problem algorithm main processing loop\n");
            if (pb) System.out.println("Progress: |==========|"); // 10 units so each symbol is 10% progress
            if (pb) System.out.println("           ");
            int cycle = 0;

            //var sw = System.Diagnostics.Stopwatch.StartNew();// ham dem thoi gian
            StopWatch s = new StopWatch();
            s.start();
            while (cycle < this.maxNumberCycles)
            {
                for (int i = 0; i < this.totalNumberBees; i++) // each bee
                {
                    switch (this.bees[i].status)
                    {
                        case 1:
                            ProcessActiveBee(i);
                            break;
                        case 2:
                            ProcessScoutBee(i);
                            break;
                        case 0:
                            //ProcessInactiveBee(i);
                            break;
                    }
                } // for each bee
                
                cycle++;

                // print a progress bar
                if (pb && cycle % increment == 0)  
                    System.out.print("^");

            } // main while processing loop
            System.out.println("\n");
            //sw.Stop();
            s.stop();
            System.out.println("Elapsed time in milliseconds: " + s.getElapsedTime()+"ms");
            //System.out.println("   Total Time: " + sw.ElapsedMilliseconds);

            if (pb) System.out.println(""); // end the progress bar
        } // Solve()

        private void ProcessInactiveBee(int i)
        {
            return; // not used in this implementation
        }

        private void ProcessActiveBee(int i) throws Exception
        {
            int[] neighbor = GenerateNeighborMemoryMatrix(bees[i].memoryMatrix); // find a neighbor solution
            double neighborQuality = MeasureOfQuality(neighbor); // get its quality
            double prob = random.nextDouble(); // used to determine if bee makes a mistake; compare against probMistake which has some small value (~0.01)
            boolean memoryWasUpdated = false; // used to determine if bee should perform a waggle dance when done
            boolean numberOfVisitsOverLimit = false; // used to determine if bee will convert to inactive status

            if (neighborQuality < bees[i].measureOfQuality) // active bee found better neighbor (< because smaller values are better)
            {
                if (prob < probMistake) // bee makes mistake: rejects a better neighbor food source
                {
                    ++bees[i].numberOfVisits; // no change to memory but update number of visits
                    if (bees[i].numberOfVisits > maxNumberVisits) numberOfVisitsOverLimit = true;
                }
                else // bee does not make a mistake: accepts a better neighbor
                {
                    System.arraycopy(neighbor,0, bees[i].memoryMatrix,0, neighbor.length); // copy neighbor location into bee's memory
                    bees[i].measureOfQuality = neighborQuality; // update the quality
                    bees[i].numberOfVisits = 0; // reset counter
                    memoryWasUpdated = true; // so that this bee will do a waggle dance 
                }
            }
            else // active bee did not find a better neighbor
            {
                //Console.WriteLine("c");
                if (prob < probMistake) // bee makes mistake: accepts a worse neighbor food source
                {
                    System.arraycopy(neighbor,0, bees[i].memoryMatrix,0, neighbor.length); // copy neighbor location into bee's memory
                    bees[i].measureOfQuality = neighborQuality; // update the quality
                    bees[i].numberOfVisits = 0; // reset
                    memoryWasUpdated = true; // so that this bee will do a waggle dance 
                }
                else // no mistake: bee rejects worse food source
                {
                    ++bees[i].numberOfVisits;
                    if (bees[i].numberOfVisits > maxNumberVisits) numberOfVisitsOverLimit = true;
                }
            }

            // at this point we need to determine a.) if the number of visits has been exceeded in which case bee becomes inactive
            // or b.) memory was updated in which case check to see if new memory is a global best, and then bee does waggle dance
            // or c.) neither in which case nothing happens (bee just returns to hive).

            if (numberOfVisitsOverLimit == true)
            {
                bees[i].status = 0; // current active bee transitions to inactive
                bees[i].numberOfVisits = 0; // reset visits (and no change to this bees memory)
                int x = random.nextInt(numberInactive); // pick a random inactive bee. x is an index into a list, not a bee ID
                bees[indexesOfInactiveBees[x]].status = 1; // make it active
                indexesOfInactiveBees[x] = i; // record now-inactive bee 'i' in the inactive list
            }
            else if (memoryWasUpdated == true) // current bee returns and performs waggle dance
            {
                // first, determine if the new memory is a global best. note that if bee has accepted a worse food source this can't be true
                if (bees[i].measureOfQuality < this.bestMeasureOfQuality) // the modified bee's memory is a new global best (< because smaller is better)
                {
                    System.arraycopy(bees[i].memoryMatrix,0, this.bestMemoryMatrix,0, bees[i].memoryMatrix.length); // update global best memory
                    this.bestMeasureOfQuality = bees[i].measureOfQuality; // update global best quality
                }
                DoWaggleDance(i);
            }
            else // number visits is not over limit and memory was not updated so do nothing (return to hive but do not waggle)
            {
                return;
            }
        } // ProcessActiveBee()

        private void ProcessScoutBee(int i) throws Exception
        {
            int[] randomFoodSource = GenerateRandomMemoryMatrix(); // scout bee finds a random food source. . . 
            double randomFoodSourceQuality = MeasureOfQuality(randomFoodSource); // and examines its quality
            if (randomFoodSourceQuality < bees[i].measureOfQuality) // scout bee has found a better solution than its current one (< because smaller measure is better)
            {
                System.arraycopy(randomFoodSource,0, bees[i].memoryMatrix,0, randomFoodSource.length); // unlike active bees, scout bees do not make mistakes
                bees[i].measureOfQuality = randomFoodSourceQuality;
                // no change to scout bee's numberOfVisits or status

                // did this scout bee find a better overall/global solution?
                if (bees[i].measureOfQuality < bestMeasureOfQuality) // yes, better overall solution (< because smaller is better)
                {
                    System.arraycopy(bees[i].memoryMatrix,0, this.bestMemoryMatrix,0, bees[i].memoryMatrix.length); // copy scout bee's memory to global best
                    this.bestMeasureOfQuality = bees[i].measureOfQuality;
                } // better overall solution

                DoWaggleDance(i); // scout returns to hive and does waggle dance

            } // if scout bee found better solution
        } // ProcessScoutBee()

        private void DoWaggleDance(int i) throws Exception
        {
            for (int ii = 0; ii < numberInactive; ++ii) // each inactive/watcher bee
            {
                int b = indexesOfInactiveBees[ii]; // index of an inactive bee
                if (bees[b].status != 0) throw new Exception("Catastrophic logic error when scout bee waggles dances");
                if (bees[b].numberOfVisits != 0) throw new Exception("Found an inactive bee with numberOfVisits != 0 in Scout bee waggle dance routine");
                if (bees[i].measureOfQuality < bees[b].measureOfQuality) // scout bee has a better solution than current inactive/watcher bee (< because smaller is better)
                {
                    double p = random.nextDouble(); // will current inactive bee be persuaded by scout's waggle dance?
                    if (this.probPersuasion > p) // this inactive bee is persuaded by the scout (usually because probPersuasion is large, ~0.90)
                    {
                        System.arraycopy(bees[i].memoryMatrix,0, bees[b].memoryMatrix,0, bees[i].memoryMatrix.length);
                        bees[b].measureOfQuality = bees[i].measureOfQuality;
                    } // inactive bee has been persuaded
                } // scout bee has better solution than watcher/inactive bee
            } // each inactive bee
        } // DoWaggleDance()

}
