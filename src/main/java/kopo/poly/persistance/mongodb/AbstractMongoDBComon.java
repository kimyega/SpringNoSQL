package kopo.poly.persistance.mongodb;

import com.mongodb.client.model.Indexes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractMongoDBComon {

    /**
     *
     * @param mongodb   접속된 Mongodb
     * @param colNm     생성할 컬렉션명
     * @return          생성 결과
     */
    protected boolean createCollection(MongoTemplate mongodb, String colNm) {

        boolean res;

        if (mongodb.collectionExists(colNm)) {
            res = false;
        } else  {
            mongodb.createCollection(colNm);
            res = true;
        }

        return res;
    }

    /**
     *
     * @param mongodb   접속된 Mongodb
     * @param colNm     생성할 컬렉션명
     * @param index     생성할 인덱스
     * @return          생성 결과
     */
    protected boolean createCollection(MongoTemplate mongodb, String colNm, String[] index ) {

        log.info("{}.createCollection Start!", this.getClass().getName());

        boolean res = false;

        if (!mongodb.collectionExists(colNm)) {

            if (index.length > 0) {

                mongodb.createCollection(colNm).createIndex(Indexes.ascending(index));
            } else  {
                mongodb.createCollection(colNm);
            }

            res = true;
        }

        log.info("{}.createCollection End!", this.getClass().getName());

        return res;
    }

    /**
     *
     * @param mongodb   접속된 MongoDB
     * @param colNm     생성할 컬렉션명
     * @param index     생성할 인덱스
     * @return          생성결과
     */
    protected boolean createCollection(MongoTemplate mongodb, String colNm, String index) {

        String[] indexArr = {index};

        return createCollection(mongodb, colNm, indexArr);
    }

    /**
     *
     * @param mongodb   접속된 MongoDB
     * @param colNm     생성할 컬렉션명
     * @return          삭제결과
     */
    protected boolean dropCollection(MongoTemplate mongodb, String colNm) {

        boolean res = false;

        if (mongodb.collectionExists(colNm)) {
            mongodb.dropCollection(colNm);
            res = true;
        }

        return res;
    }
}
