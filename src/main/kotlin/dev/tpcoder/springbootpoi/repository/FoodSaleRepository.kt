package dev.tpcoder.springbootpoi.repository

import dev.tpcoder.springbootpoi.model.FoodSale
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodSaleRepository : MongoRepository<FoodSale, ObjectId>