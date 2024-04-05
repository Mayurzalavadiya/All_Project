fun main() {
    val nishita = Nishita()
    println(nishita.age)
    nishita.age = 25
    println(nishita.age)
    println(nishita.getYearsOfEducation())
    nishita.setQualification(Qualification.DIPLOMA)
    println(nishita.getQualification())
    println(nishita.getYearsOfEducation())
    nishita.setQualification(Qualification.DEGREE)
    println(nishita.getQualification())
    println(nishita.getYearsOfEducation())
    nishita.setQualification(Qualification.MASTER)
    println(nishita.getQualification())
    println(nishita.getYearsOfEducation())

//     val isSurnameChanged = nishita.changeNishitasSurname()
//     println("Kya surname change hui? $isSurnameChanged")
}

open class Girl {
    protected open fun setSurname() {
        println("Initial Surname is: Girl")
    }
}

class Qualification {
    companion object {
        internal const val HSC = "hsc"
        internal const val DIPLOMA = "diploma"
        internal const val DEGREE = "degree"
        internal const val MASTER = "master"
    }
}

class Nishita : Girl() {
    private var isNishitaAgreedForXyzSurname = true
    private var didCourtAgreeForSurnameChange = true
    private var qualification = Qualification.HSC
    private var yearsOfEducation = 12
    internal var age = 23

    public fun setQualification(qualification: String) {
        addThreeYearsOfEducation()
        this.qualification = qualification
    }

    public fun getQualification(): String {
        return qualification
    }

    private fun addThreeYearsOfEducation() {
        yearsOfEducation += 3
    }

    public fun getYearsOfEducation(): Int {
        return yearsOfEducation
    }

    override fun setSurname() {
        super.setSurname()
        println("Initial Surname is: Nishita")
    }

    internal fun changeNishitasSurname(): Boolean {
        setSurname()
        if (didCourtAgreeForSurnameChange) {
            if (isNishitaAgreedForXyzSurname) {
                changeSurnameToXyz()
                return true
            } else {
                changeSurnameToPqr()
                return true
            }
        }

        return false
    }

    private fun changeSurnameToXyz() {
        println("Surname changed to Xyz")
    }

    private fun changeSurnameToPqr() {
        println("Surname changed to Pqr")
    }
}