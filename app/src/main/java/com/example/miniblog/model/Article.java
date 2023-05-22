package com.example.miniblog.model;

public class Article {
  private  String titre;
  private  String contenue;
  public Article(String titre,String contenue){
      this.titre = titre;
      this.contenue = contenue;
  }
    public String getTitre(){return this.titre;}
    public String getContenue(){return this.contenue;}
}
