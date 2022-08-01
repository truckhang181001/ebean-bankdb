package repository;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

/**
 * Custom execution context, so that blocking database operations don't
 * happen on the rendering thread pool.
 *
 * @link https://www.playframework.com/documentation/latest/ThreadPools
 */
public class DatabaseExecutionContext extends CustomExecutionContext {

    @Inject
    public DatabaseExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "database.dispatcher");
    }

    //    Select dữ liệu lớn, lấy dữ liệu phân trang
    //    Cơ chể Index của ORM
    //    Sử dụng JDBC trực tiếp dùng như nào?
    //    Cơ chế của Ebean với MariaDB, kết nối Ebean xuống MariaDB
}