package dao;

import java.util.Map;
import java.util.TreeMap;

public class DistributionsDao {
    private Map<String,Integer> distributions = new TreeMap<>();

    public DistributionsDao() {
        this.distributions.put("test",40);
        this.distributions.put("quiz",20);
        this.distributions.put("labWork",10);
        this.distributions.put("project",30);
    }

    public Map<String, Integer> getDistributions() {
        return this.distributions;
    }

    public void setDistributions(Map<String, Integer> distributions) {
        this.distributions = distributions;
    }

    public int getCategory(String key){
        return this.distributions.get(key);
    }
}
