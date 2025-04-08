package com.safestwallet.sw.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WalletDao_Impl implements WalletDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WalletEntity> __insertionAdapterOfWalletEntity;

  private final EntityDeletionOrUpdateAdapter<WalletEntity> __deletionAdapterOfWalletEntity;

  private final EntityDeletionOrUpdateAdapter<WalletEntity> __updateAdapterOfWalletEntity;

  public WalletDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWalletEntity = new EntityInsertionAdapter<WalletEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `wallet_table` (`id`,`name`,`publicKey`,`encryptedPrivateKey1`,`encryptedPrivateKey2`,`encryptedSeedPhrase`,`encryptedOtpSeed`,`timeCreated`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WalletEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getPublicKey() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPublicKey());
        }
        if (value.getEncryptedPrivateKey1() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEncryptedPrivateKey1());
        }
        if (value.getEncryptedPrivateKey2() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEncryptedPrivateKey2());
        }
        if (value.getEncryptedSeedPhrase() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEncryptedSeedPhrase());
        }
        if (value.getEncryptedOtpSeed() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEncryptedOtpSeed());
        }
        stmt.bindLong(8, value.getTimeCreated());
      }
    };
    this.__deletionAdapterOfWalletEntity = new EntityDeletionOrUpdateAdapter<WalletEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `wallet_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WalletEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfWalletEntity = new EntityDeletionOrUpdateAdapter<WalletEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `wallet_table` SET `id` = ?,`name` = ?,`publicKey` = ?,`encryptedPrivateKey1` = ?,`encryptedPrivateKey2` = ?,`encryptedSeedPhrase` = ?,`encryptedOtpSeed` = ?,`timeCreated` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WalletEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getPublicKey() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPublicKey());
        }
        if (value.getEncryptedPrivateKey1() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEncryptedPrivateKey1());
        }
        if (value.getEncryptedPrivateKey2() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEncryptedPrivateKey2());
        }
        if (value.getEncryptedSeedPhrase() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEncryptedSeedPhrase());
        }
        if (value.getEncryptedOtpSeed() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEncryptedOtpSeed());
        }
        stmt.bindLong(8, value.getTimeCreated());
        if (value.getId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getId());
        }
      }
    };
  }

  @Override
  public Object insertWallet(final WalletEntity wallet,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWalletEntity.insert(wallet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteWallet(final WalletEntity wallet,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfWalletEntity.handle(wallet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateWallet(final WalletEntity wallet,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfWalletEntity.handle(wallet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public LiveData<List<WalletEntity>> getAllWallets() {
    final String _sql = "SELECT * FROM wallet_table ORDER BY timeCreated DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"wallet_table"}, false, new Callable<List<WalletEntity>>() {
      @Override
      public List<WalletEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPublicKey = CursorUtil.getColumnIndexOrThrow(_cursor, "publicKey");
          final int _cursorIndexOfEncryptedPrivateKey1 = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedPrivateKey1");
          final int _cursorIndexOfEncryptedPrivateKey2 = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedPrivateKey2");
          final int _cursorIndexOfEncryptedSeedPhrase = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedSeedPhrase");
          final int _cursorIndexOfEncryptedOtpSeed = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedOtpSeed");
          final int _cursorIndexOfTimeCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "timeCreated");
          final List<WalletEntity> _result = new ArrayList<WalletEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final WalletEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPublicKey;
            if (_cursor.isNull(_cursorIndexOfPublicKey)) {
              _tmpPublicKey = null;
            } else {
              _tmpPublicKey = _cursor.getString(_cursorIndexOfPublicKey);
            }
            final String _tmpEncryptedPrivateKey1;
            if (_cursor.isNull(_cursorIndexOfEncryptedPrivateKey1)) {
              _tmpEncryptedPrivateKey1 = null;
            } else {
              _tmpEncryptedPrivateKey1 = _cursor.getString(_cursorIndexOfEncryptedPrivateKey1);
            }
            final String _tmpEncryptedPrivateKey2;
            if (_cursor.isNull(_cursorIndexOfEncryptedPrivateKey2)) {
              _tmpEncryptedPrivateKey2 = null;
            } else {
              _tmpEncryptedPrivateKey2 = _cursor.getString(_cursorIndexOfEncryptedPrivateKey2);
            }
            final String _tmpEncryptedSeedPhrase;
            if (_cursor.isNull(_cursorIndexOfEncryptedSeedPhrase)) {
              _tmpEncryptedSeedPhrase = null;
            } else {
              _tmpEncryptedSeedPhrase = _cursor.getString(_cursorIndexOfEncryptedSeedPhrase);
            }
            final String _tmpEncryptedOtpSeed;
            if (_cursor.isNull(_cursorIndexOfEncryptedOtpSeed)) {
              _tmpEncryptedOtpSeed = null;
            } else {
              _tmpEncryptedOtpSeed = _cursor.getString(_cursorIndexOfEncryptedOtpSeed);
            }
            final long _tmpTimeCreated;
            _tmpTimeCreated = _cursor.getLong(_cursorIndexOfTimeCreated);
            _item = new WalletEntity(_tmpId,_tmpName,_tmpPublicKey,_tmpEncryptedPrivateKey1,_tmpEncryptedPrivateKey2,_tmpEncryptedSeedPhrase,_tmpEncryptedOtpSeed,_tmpTimeCreated);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<WalletEntity> getWalletById(final String walletId) {
    final String _sql = "SELECT * FROM wallet_table WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (walletId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, walletId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"wallet_table"}, false, new Callable<WalletEntity>() {
      @Override
      public WalletEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPublicKey = CursorUtil.getColumnIndexOrThrow(_cursor, "publicKey");
          final int _cursorIndexOfEncryptedPrivateKey1 = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedPrivateKey1");
          final int _cursorIndexOfEncryptedPrivateKey2 = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedPrivateKey2");
          final int _cursorIndexOfEncryptedSeedPhrase = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedSeedPhrase");
          final int _cursorIndexOfEncryptedOtpSeed = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedOtpSeed");
          final int _cursorIndexOfTimeCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "timeCreated");
          final WalletEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPublicKey;
            if (_cursor.isNull(_cursorIndexOfPublicKey)) {
              _tmpPublicKey = null;
            } else {
              _tmpPublicKey = _cursor.getString(_cursorIndexOfPublicKey);
            }
            final String _tmpEncryptedPrivateKey1;
            if (_cursor.isNull(_cursorIndexOfEncryptedPrivateKey1)) {
              _tmpEncryptedPrivateKey1 = null;
            } else {
              _tmpEncryptedPrivateKey1 = _cursor.getString(_cursorIndexOfEncryptedPrivateKey1);
            }
            final String _tmpEncryptedPrivateKey2;
            if (_cursor.isNull(_cursorIndexOfEncryptedPrivateKey2)) {
              _tmpEncryptedPrivateKey2 = null;
            } else {
              _tmpEncryptedPrivateKey2 = _cursor.getString(_cursorIndexOfEncryptedPrivateKey2);
            }
            final String _tmpEncryptedSeedPhrase;
            if (_cursor.isNull(_cursorIndexOfEncryptedSeedPhrase)) {
              _tmpEncryptedSeedPhrase = null;
            } else {
              _tmpEncryptedSeedPhrase = _cursor.getString(_cursorIndexOfEncryptedSeedPhrase);
            }
            final String _tmpEncryptedOtpSeed;
            if (_cursor.isNull(_cursorIndexOfEncryptedOtpSeed)) {
              _tmpEncryptedOtpSeed = null;
            } else {
              _tmpEncryptedOtpSeed = _cursor.getString(_cursorIndexOfEncryptedOtpSeed);
            }
            final long _tmpTimeCreated;
            _tmpTimeCreated = _cursor.getLong(_cursorIndexOfTimeCreated);
            _result = new WalletEntity(_tmpId,_tmpName,_tmpPublicKey,_tmpEncryptedPrivateKey1,_tmpEncryptedPrivateKey2,_tmpEncryptedSeedPhrase,_tmpEncryptedOtpSeed,_tmpTimeCreated);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getWalletCount(final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT(*) FROM wallet_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getWalletByIdSync(final String walletId,
      final Continuation<? super WalletEntity> continuation) {
    final String _sql = "SELECT * FROM wallet_table WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (walletId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, walletId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WalletEntity>() {
      @Override
      public WalletEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPublicKey = CursorUtil.getColumnIndexOrThrow(_cursor, "publicKey");
          final int _cursorIndexOfEncryptedPrivateKey1 = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedPrivateKey1");
          final int _cursorIndexOfEncryptedPrivateKey2 = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedPrivateKey2");
          final int _cursorIndexOfEncryptedSeedPhrase = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedSeedPhrase");
          final int _cursorIndexOfEncryptedOtpSeed = CursorUtil.getColumnIndexOrThrow(_cursor, "encryptedOtpSeed");
          final int _cursorIndexOfTimeCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "timeCreated");
          final WalletEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPublicKey;
            if (_cursor.isNull(_cursorIndexOfPublicKey)) {
              _tmpPublicKey = null;
            } else {
              _tmpPublicKey = _cursor.getString(_cursorIndexOfPublicKey);
            }
            final String _tmpEncryptedPrivateKey1;
            if (_cursor.isNull(_cursorIndexOfEncryptedPrivateKey1)) {
              _tmpEncryptedPrivateKey1 = null;
            } else {
              _tmpEncryptedPrivateKey1 = _cursor.getString(_cursorIndexOfEncryptedPrivateKey1);
            }
            final String _tmpEncryptedPrivateKey2;
            if (_cursor.isNull(_cursorIndexOfEncryptedPrivateKey2)) {
              _tmpEncryptedPrivateKey2 = null;
            } else {
              _tmpEncryptedPrivateKey2 = _cursor.getString(_cursorIndexOfEncryptedPrivateKey2);
            }
            final String _tmpEncryptedSeedPhrase;
            if (_cursor.isNull(_cursorIndexOfEncryptedSeedPhrase)) {
              _tmpEncryptedSeedPhrase = null;
            } else {
              _tmpEncryptedSeedPhrase = _cursor.getString(_cursorIndexOfEncryptedSeedPhrase);
            }
            final String _tmpEncryptedOtpSeed;
            if (_cursor.isNull(_cursorIndexOfEncryptedOtpSeed)) {
              _tmpEncryptedOtpSeed = null;
            } else {
              _tmpEncryptedOtpSeed = _cursor.getString(_cursorIndexOfEncryptedOtpSeed);
            }
            final long _tmpTimeCreated;
            _tmpTimeCreated = _cursor.getLong(_cursorIndexOfTimeCreated);
            _result = new WalletEntity(_tmpId,_tmpName,_tmpPublicKey,_tmpEncryptedPrivateKey1,_tmpEncryptedPrivateKey2,_tmpEncryptedSeedPhrase,_tmpEncryptedOtpSeed,_tmpTimeCreated);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
