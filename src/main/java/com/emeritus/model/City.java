package com.emeritus.model;

public class City {
    private String country, city, accentCity, region;
    private int population;
    private double latitude, longitude;

    public City() {
        
    }

    public City(String country, String city, String accentCity, String region, int population, double latitude, double longitude) {
        this.country = country;
        this.city = city;
        this.accentCity = accentCity;
        this.region = region;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%d,%f,%f", country, city, accentCity, region, population, latitude, longitude);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAccentCity() {
        return accentCity;
    }

    public void setAccentCity(String accentCity) {
        this.accentCity = accentCity;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city1 = (City) o;

        if (getPopulation() != city1.getPopulation()) return false;
        if (Double.compare(city1.getLatitude(), getLatitude()) != 0) return false;
        if (Double.compare(city1.getLongitude(), getLongitude()) != 0) return false;
        if (getCountry() != null ? !getCountry().equals(city1.getCountry()) : city1.getCountry() != null) return false;
        if (getCity() != null ? !getCity().equals(city1.getCity()) : city1.getCity() != null) return false;
        if (getAccentCity() != null ? !getAccentCity().equals(city1.getAccentCity()) : city1.getAccentCity() != null)
            return false;
        return getRegion() != null ? getRegion().equals(city1.getRegion()) : city1.getRegion() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getCountry() != null ? getCountry().hashCode() : 0;
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getAccentCity() != null ? getAccentCity().hashCode() : 0);
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        result = 31 * result + getPopulation();
        temp = Double.doubleToLongBits(getLatitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
