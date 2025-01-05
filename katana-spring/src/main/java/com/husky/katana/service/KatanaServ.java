package com.husky.katana.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.protobuf.Empty;
import com.husky.katana.KatanaDTO;
import com.husky.katana.KatanaResp;
import com.husky.katana.KatanaServGrpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class KatanaServ extends KatanaServGrpc.KatanaServImplBase {
	
	@Override
	public void getKatana(Empty request, StreamObserver<KatanaResp> respObs) {
		final KatanaDTO katanaDTO = KatanaDTO.newBuilder()
			    .setIdKatana(UUID.randomUUID().toString())
			    .setNomKatana("Hattori Hanzo")
			    .setNumKatana(1)
			    .build();
		
		final List<KatanaDTO> listKat = new ArrayList<>();
		listKat.add(katanaDTO);
		
		respObs.onNext(KatanaResp.newBuilder().addAllKatana(listKat).build());
//		respObs.onNext(CategoriaResp.newBuilder().addAllCategorias(catDto).build());
		respObs.onCompleted();
	}

	
}