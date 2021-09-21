package DAO;

import java.util.ArrayList;

import Model.Purchase;

public interface IPurchaseDAO {

    Purchase findById(int id);
    ArrayList<Purchase> findAll();

    int add(Purchase p);

    Purchase findByUserId(int userId);

    int findByItemIdAndPurchaseId(int itemId, int purchaseId);

    int getPurchaseIdById(int id);

    int getBuyerIdByPurchaseId(int id);
}
