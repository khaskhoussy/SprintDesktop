/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entits.LignePack;
import entits.PackDecoration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdelli
 */
public interface IPackService {
    
    public List<LignePack> getLignePackByPack(PackDecoration pack);
    
}
