package com.websarva.wings.android.intentsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //画面部品ListViewを取得
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        //SimpleAdapterで使用するMutableListオブジェクトを用意
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        //「唐揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        var menu = mutableMapOf("name" to "唐揚げ定食", "price" to "800円")
        menuList.add(menu)
        //「~定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "生姜焼き定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ステーキ定食", "price" to "1000円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "野菜炒め定食", "price" to "750円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "とんかつ定食", "price" to "900円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ミンチかつ定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "チキンかつ定食", "price" to "900円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "コロッケ定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "回鍋肉定食", "price" to "750円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "麻婆豆腐定食", "price" to "800円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "青椒肉絲定食", "price" to "800円")
        menuList.add(menu)

        //SimpleAdapter第4引数from用のデータの用意
        val from = arrayOf("name", "price")
        //SimpleAdapter第5引数to用のデータの用意
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        //SimpleAdapterを生成
        val adapter = SimpleAdapter(
            this@MainActivity, menuList,
            android.R.layout.simple_list_item_2, from, to
        )
        //アダプタの登録
        lvMenu.adapter = adapter

        //リストタップのリスナクラス登録
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    //リストがタップされた時の処理が記述されたメンバクラス
    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            //タップされた行のデータを取得。SimpleAdapterでは1行分のデータはMutableMap型!
            val item =parent.getItemAtPosition(position) as MutableMap<String, String>
            //定食名と金額を取得
            val menuName = item["name"]
            val menuPrice = item["price"]

            //(1)インテントオブジェクトを生成
            val intent2MenuThanks = Intent(this@MainActivity, MenuThanksActivty::class.java)
            //(2)第2画面に送るデータを格納
            intent2MenuThanks.putExtra("menuName", menuName)
            intent2MenuThanks.putExtra("menuPrice",menuPrice)
            //(1)(2)はJavaでの記述方法。kotlinだと、apply関数を使うと、インスタンスを表す変数を減らせる。
            //val intent2MenuThanks = Intent(this@MainActivity, ···).apply {
            //  putExtra("menuName", menuName)
            //  putExtra("menuPrice", menuPrice) }
            //(3)第2画面の起動
            startActivity(intent2MenuThanks)
        }
    }
}