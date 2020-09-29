package com.javastudio.tutorial.coherence.model;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofSerializer;
import com.tangosol.io.pof.PofWriter;

import java.io.IOException;

public class AccountPofSerializer implements PofSerializer {
    @Override
    public void serialize(PofWriter out, Object value) throws IOException {
        Account account = (Account) value;
        out.writeLong(0, account.getAccountIsn());
        out.writeRemainder(null);
    }

    @Override
    public Object deserialize(PofReader in) throws IOException {
        long accountIsn = in.readLong(0);
        Account account = new Account(accountIsn);
        in.readRemainder();
        return account;
    }
}
