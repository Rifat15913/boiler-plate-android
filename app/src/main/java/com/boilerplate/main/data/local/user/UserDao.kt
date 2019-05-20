package com.boilerplate.main.data.local.user

import androidx.room.Dao
import com.boilerplate.utils.libs.room.BaseDao

@Dao
interface UserDao : BaseDao<UserEntity>