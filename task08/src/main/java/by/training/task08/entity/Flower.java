package by.training.task08.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Flower {

    private String name;
    private String soil;
    private String origin;
    private String multiplying;
    private Date growDate;
    private String itemName;
    private VisualParams visualParams;
    private GrowingTips growingTips;
    private boolean isPoisoning;

    public Flower() {
        visualParams = new VisualParams();
        growingTips = new GrowingTips();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("\nFlower:\n\t");
        sb.append("name: ").append(name);
        sb.append("\n\tsoil: ").append(soil);
        sb.append("\n\torigin: ").append(origin);
        sb.append("\n\tmultiplying: ").append(multiplying);
        sb.append("\n\titemName: ").append(itemName);
        sb.append("\n\tgrowDate: ").append(growDate);
        sb.append("\n\tisPoisining: ").append(isPoisoning);
        sb.append(visualParams);
        sb.append(growingTips);
        return sb.toString();
    }

    @Data
    public class VisualParams {
        private String stColor;
        private String lfColor;
        private String avSize;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("\n\tVisualParams:\n\t\t");
            sb.append("stColor: ").append(stColor);
            sb.append("\n\t\tlfColor: ").append(lfColor);
            sb.append("\n\t\tavSize: ").append(avSize);
            return sb.toString();
        }
    }

    @Data
    public class GrowingTips {
        private double temperature;
        private double water;
        private boolean light;
        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("\n\tGrowingTips:\n\t\t");
            sb.append("temperature: ").append(temperature);
            sb.append("\n\t\twater: ").append(water);
            sb.append("\n\t\tlight: ").append(light);
            sb.append('\n');
            return sb.toString();
        }
    }
}
