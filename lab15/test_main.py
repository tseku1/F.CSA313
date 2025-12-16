import pytest
from main import is_valid_email

def test_valid_email():
    assert is_valid_email("user@gmail.com") is True

def test_missing_at_symbol():
    assert is_valid_email("usergmail.com") is False

def test_blocked_domain():
    assert is_valid_email("user@test.com") is False

def test_empty_string():
    assert is_valid_email("") is False

def test_only_at_symbol():
    assert is_valid_email("@") is True

def test_multiple_at_symbols():
    assert is_valid_email("a@b@c.com") is True
