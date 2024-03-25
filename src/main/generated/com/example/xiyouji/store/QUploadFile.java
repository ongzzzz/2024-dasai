package com.example.xiyouji.store;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUploadFile is a Querydsl query type for UploadFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUploadFile extends EntityPathBase<UploadFile> {

    private static final long serialVersionUID = -1211168836L;

    public static final QUploadFile uploadFile = new QUploadFile("uploadFile");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath storeFileName = createString("storeFileName");

    public QUploadFile(String variable) {
        super(UploadFile.class, forVariable(variable));
    }

    public QUploadFile(Path<? extends UploadFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUploadFile(PathMetadata metadata) {
        super(UploadFile.class, metadata);
    }

}

