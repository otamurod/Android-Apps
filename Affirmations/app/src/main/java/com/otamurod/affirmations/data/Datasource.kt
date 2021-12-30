package com.otamurod.affirmations.data

import com.otamurod.affirmations.R
import com.otamurod.affirmations.model.Affirmation

class Datasource{
    fun loadAffirmations(): List<Affirmation>{
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.motorbike),
            Affirmation(R.string.affirmation2, R.drawable.motorbike),
            Affirmation(R.string.affirmation3, R.drawable.motorbike),
            Affirmation(R.string.affirmation4, R.drawable.motorbike),
            Affirmation(R.string.affirmation5, R.drawable.motorbike),
            Affirmation(R.string.affirmation6, R.drawable.motorbike),
            Affirmation(R.string.affirmation7, R.drawable.motorbike),
            Affirmation(R.string.affirmation8, R.drawable.motorbike),
            Affirmation(R.string.affirmation9, R.drawable.motorbike),
            Affirmation(R.string.affirmation10, R.drawable.motorbike))
    }
}