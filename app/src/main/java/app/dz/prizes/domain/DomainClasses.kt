package app.dz.prizes.domain

data class Subscription(
        val id: Long,
        val title: String,
        val description: String,
        val iconUrl: String)

/*
data class SubscriptionList(val list: List<Subscription>) {

    val size: Int
        get() = list.size

    operator fun get(position: Int) = list[position]

}
*/

